package com.framgia.dao;

import com.framgia.model.Vote;

public interface VoteDAO extends IGenericDAO<Integer, Vote> {
	Vote findVoteToDelete(Integer idImage, Integer idUser);
}
