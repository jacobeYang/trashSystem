package com.jacob.trash.service;

import com.jacob.trash.domain.Type;
import org.springframework.stereotype.Service;

@Service("TrashTypeService")
public class TrashTypeService {
    private static final Type[] types={new Type(0,"协调器"),new Type(1,"路由节点"),new Type(2,"终端节点")};

    public Type[] getTypes(){
        return types;
    }

    public String getTypeName(int value){
        for(Type type:types){
            if(type.getValue() == value){
                return type.getName();
            }
        }
        return null;
    }


}
