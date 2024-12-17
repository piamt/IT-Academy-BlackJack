package com.pia.itacademy.BlackJack.mongodb.service;

import com.pia.itacademy.BlackJack.mongodb.model.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public Integer getSequenceNumber(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DbSequence dbSequence = mongoOperations.findAndModify(query,
                update,
                options().returnNew(true).upsert(true),
                DbSequence.class);

        return !Objects.isNull(dbSequence) ? dbSequence.getSeq() : 1;
    }
}
