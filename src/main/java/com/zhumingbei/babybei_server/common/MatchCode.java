package com.zhumingbei.babybei_server.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author fadedfate
 * @date Created at 2019/9/5 14:02
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class MatchCode implements Serializable {
    private static final long serialVersionUID = 4973737849598891352L;
    private Integer userID;
    private Long code;
    private Long createdTime;
    private Long ttl;

    public MatchCode(int userID, Long code) {
        this(userID, code, System.currentTimeMillis(), 3600000L);
    }
}
