/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.model.gallery;

/**
 *
 * @author Tonu
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class GalleriaBean implements Serializable {

    private static long serialVersionUID = 1L;
    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 7; i++) {
            images.add("galleria" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}
