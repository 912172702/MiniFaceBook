package com.dbc.minifb.repository;

import com.dbc.minifb.entity.Comment;
import com.dbc.minifb.entity.Invitation;
import com.dbc.minifb.entity.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/3 6:21 PM
 * @Description: You Know
 * @Version 1.0
 */
public interface CommentRepository extends Neo4jRepository<Comment, Long> {
    @Query("match(n:Comment)-[r:BELONG_TO]->(b:Invitation) where id(b)={0} return n order by n.time")
    List<Comment> findByBelongToOrderByTime(Long id);

    @Query("match(n:Comment)-[r:WRITE_BY]->(b:User) where id(n) = {0} return b")
    User findUserWrittenComment(Long commentId);
}
