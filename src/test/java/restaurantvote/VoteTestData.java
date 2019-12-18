package restaurantvote;

import restaurantvote.model.Vote;

import static restaurantvote.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE_ID = START_SEQ + 2;

    public static final Vote VOTE_USER_1 = new Vote(VOTE_ID);
    public static final Vote VOTE_USER_2 = new Vote(VOTE_ID + 1);
    public static final Vote VOTE_USER_3 = new Vote(VOTE_ID + 2);

    public static final Vote VOTE_ADMIN_1 = new Vote(VOTE_ID + 3);
    public static final Vote VOTE_ADMIN_2 = new Vote(VOTE_ID + 4);
}
