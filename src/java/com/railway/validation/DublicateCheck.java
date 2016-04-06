/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.railway.validation;

import java.util.List;

/**
 *
 * @author Enamul
 */
public class DublicateCheck<T> {

    public boolean isDublicate(T value, List<T> list) {
        boolean b = false;
        for (T obj : list) {
            if (value.equals(obj)) {
                b = true;
                break;
            }
        }
        return b;

    }
}
