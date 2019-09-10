package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;


@Component
public class ArticleMapper {

    private Logger logger = LoggerFactory.getLogger(ArticleMapper.class);

    public ArticleDto map(Article article){
        ArticleDto payload = new ArticleDto();

        if (article == null) {
            return payload;
        }

        payload.setAuthor(article.getAuthor());
        payload.setDescription(article.getDescription());
        payload.setId(article.getId());
        payload.setTitle(article.getTitle());

        ArrayList<ArticleBlock> blocks = new ArrayList<>(article.getBlocks());

        List<ArticleBlock> found = new ArrayList<>();
        for(ArticleBlock block: blocks){
            if(block.getSortIndex() == 0){
                found.add(block);
                logger.info("ArticleBlock has no sort index... " + block);
            }
        }
        blocks.removeAll(found);

        blocks.sort(Comparator.comparing(ArticleBlock::getSortIndex));
        payload.setBlocks((Collection)blocks);

        return payload;
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }
}

