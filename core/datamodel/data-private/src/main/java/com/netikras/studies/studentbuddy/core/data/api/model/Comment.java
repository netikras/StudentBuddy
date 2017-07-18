package com.netikras.studies.studentbuddy.core.data.api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@Entity
@Table(name = "comment")
public class Comment {

    private String id;

    private Date createdOn;
    private Date updatedOn;

    private Person author;

    private String title;
    private String data;
    private String category;
    private List<String> tags;


}
