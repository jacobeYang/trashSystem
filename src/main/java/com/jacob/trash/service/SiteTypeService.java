package com.jacob.trash.service;

import com.jacob.trash.domain.Type;
import org.springframework.stereotype.Service;

@Service("SiteTypeService")
public class SiteTypeService {
    private static final Type[] types={new Type(0,"园区"),new Type(1,"大楼"),new Type(2,"其他")};

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
