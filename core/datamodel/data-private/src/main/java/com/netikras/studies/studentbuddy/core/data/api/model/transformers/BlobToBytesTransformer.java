package com.netikras.studies.studentbuddy.core.data.api.model.transformers;

import com.netikras.tools.common.model.mapper.MappingTransformer;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

public class BlobToBytesTransformer implements MappingTransformer {

    @Override
    public Object toDto(Object object) {
        if (object == null) {
            return null;
        }
        Blob blob = (Blob) object;
        byte[] data = null;
        try {
            data = blob.getBytes(1, (int) blob.length());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    public Object fromDto(Object object) {
        if (object == null) {
            return null;
        }
        byte[] data = (byte[]) object;

        Blob blob = null;
        try {
            blob = new SerialBlob(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blob;
    }
}
