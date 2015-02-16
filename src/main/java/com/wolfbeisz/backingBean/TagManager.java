package com.wolfbeisz.backingBean;


import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Philipp on 27.12.2014.
 */
@javax.faces.view.ViewScoped
@Named
public class TagManager implements Serializable{
    private String tagToAdd = "";
    private List<String> tags = new ArrayList<String>();

    public String addTag(){
        tags.add(tagToAdd);
        tagToAdd = "";
        return null;
    }


    public String getTagToAdd() {
        return tagToAdd;
    }

    public void setTagToAdd(String tagToAdd) {
        this.tagToAdd = tagToAdd;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
