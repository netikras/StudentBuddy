package com.netikras.studies.studentbuddy.core.data.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface JpaRepo<T> extends JpaRepository<T, String> {

    /**
     * Translate pseudo-regex query to SQL search string<br/>
     * Conversions:
     * <pre>
     *     % -> [%] (literal percent in 'like' query)
     *     * -> %
     *     ^ -> enforces to search for strings starting with given values. Only effective as a first char in query
     *     $ -> enforces to search for strings ending with given values. Only effective as a last char in query
     *
     *     \% -> %
     *     \\ -> \
     *     \^ -> ^ (only works on a first char)
     *     \$ -> $ (only works on a last char)
     *     \* -> *
     * </pre>
     * @param substring pseudo-regex query.
     * @return
     */
    default String wrapSearchString(String substring) {
        String result = null;

        if (substring == null || substring.isEmpty()) {
            return "";
        }

        boolean escape = false;
        boolean endsWithDollar = false;
        char c = 0;
        char[] data = substring.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        if (data[0] != '^') {
            stringBuilder.append('%');
        }

        for (int i = 0; i < data.length; i++) {
            c = data[i];

            switch (c) {
                case '%':
                    stringBuilder.append("[%]");
                    break;
                case '*':
                    if (escape) {
                        stringBuilder.append(c);
                        escape = false;
                    } else {
                        stringBuilder.append('%');
                    }
                    break;
                case '^':
                    if (escape) {
                        if (i == 1) { // first char is an escaped caret
                            stringBuilder.append(c);
                        } else {
                            stringBuilder.append("\\^");
                        }
                        escape = false;
                    } else {
                        if (i == 0) { // first char is a caret
                            break;
                        } else {
                            stringBuilder.append('%');
                        }
                    }
                    break;
                case '$':
                    if (escape) {
                        if (i + 1 == data.length) {
                            stringBuilder.append(c);
                        } else {
                            stringBuilder.append("\\$");
                        }
                        escape = false;
                    } else {
                        if (i + 1 == data.length) {
                            // skip
                            endsWithDollar = true;
                        } else {
                            stringBuilder.append(c);
                        }
                    }

                    break;
                case '\\':
                    if (escape) {
                        stringBuilder.append(c);
                        escape = false;
                    } else {
                        escape = true;
                    }
                    break;
                default:
                    if (escape) {
                        stringBuilder.append('\\');
                        escape = false;
                    }
                    stringBuilder.append(c);
                    break;
            }
        }

        if (!endsWithDollar) {
            stringBuilder.append('%');
        }

        result = stringBuilder.toString();

        return result;
    }

}
