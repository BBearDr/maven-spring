package com.bbear.example.test;

import java.util.List;

/**
 * @author junxiongchen
 * @date 2018/03/08
 */
public interface Protocol {
    String getStrInfo();
    List<String> getListInfo();

    <T> T export();
}
