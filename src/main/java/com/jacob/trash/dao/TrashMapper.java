package com.jacob.trash.dao;

import com.jacob.trash.domain.Trash;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrashMapper {
    List<Trash> selectAll();

    List<Trash> selectByType(@Param("type")int type);
    List<Trash> selectIn(List<String> hasTrashIdList, int typeValue);

    int deleteByPrimaryKey(String id);

    int insert(Trash record);

    int insertSelective(Trash record);

    Trash selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Trash record);

    int updateByPrimaryKey(Trash record);

    List<Trash> selectNoIn(List<String> hasTrashIdList, int typeValue);
}