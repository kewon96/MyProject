package com.myproject.common;

import java.util.List;

/**
 * 기본적으로 포함되어야하는 Service Methods
 */
public interface BaseService {
    /** 여러개 조회 */
    List<Object> searchList(Object param);

    /** 단일 조회 */
    Object searchOne(Object param);

    /** 추가 */
    int addData(Object param);

    /** 수정 */
    int editData(Object param);

    /** 제거 */
    int removeData(Object param);
}
