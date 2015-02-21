package com.wolfbeisz.util;

import com.wolfbeisz.model.database.Document;
import com.wolfbeisz.model.database.Revision;
import com.wolfbeisz.model.database.User;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Philipp on 21.02.2015.
 */
public class ModelUtil {
    public static Revision createRevision(Timestamp createdStamp, User createdBy, Document document, byte[] fileContent,
                                          BigDecimal version, String mimeType) {
        Revision r = new Revision();
        r.setCreatedStamp(createdStamp);
        r.setCreatedBy(createdBy);
        r.setDocument(document);
        r.setFilecontent(fileContent);
        r.setVersion(version);
        r.setMimetype(mimeType);
        return r;
    }
}
