package com.wolfbeisz.service;

import com.google.common.base.Splitter;
import com.wolfbeisz.model.database.Tag;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp on 21.02.2015.
 */
public class TagService {
    public List<String> parseTags(String commaSeparatedTags) {
        Iterable<String> tagsIterable = Splitter.on(',').trimResults().omitEmptyStrings().split(commaSeparatedTags);
        List<String> tags  = new ArrayList<String>();
        for (String tag : tagsIterable) {
            tags.add(tag);
        }
        return tags;
    }
}
