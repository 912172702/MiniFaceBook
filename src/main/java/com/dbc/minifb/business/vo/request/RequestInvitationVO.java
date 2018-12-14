package com.dbc.minifb.business.vo.request;

import com.dbc.minifb.entity.User;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/3 11:24 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInvitationVO {
    private String Content;
}
