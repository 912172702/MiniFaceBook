package com.dbc.minifb.business.vo.response;

import com.dbc.minifb.entity.Comment;
import com.dbc.minifb.entity.Invitation;
import com.dbc.minifb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/4 6:33 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInvitationVO {
    private Invitation invitation;
    private List<Comment> comments;
}
