package com.yezi.chet.community.operation;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.dao.ChetDao;

public class TryCommunityOpeartion extends BaseOperation {

    public TryCommunityOpeartion(ChetDao excuteSqlLite) {
        super(excuteSqlLite);
    }

    @Override
    public int opeartion(SendInfo data) {
        return Permission.COMMUNITY_TRY;
    }
}
