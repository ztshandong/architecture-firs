package com.zhangtao.domain.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDetailsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserDetailsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDIdIsNull() {
            addCriterion("UserDetails.d_id is null");
            return (Criteria) this;
        }

        public Criteria andDIdIsNotNull() {
            addCriterion("UserDetails.d_id is not null");
            return (Criteria) this;
        }

        public Criteria andDIdEqualTo(Long value) {
            addCriterion("UserDetails.d_id =", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotEqualTo(Long value) {
            addCriterion("UserDetails.d_id <>", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdGreaterThan(Long value) {
            addCriterion("UserDetails.d_id >", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdGreaterThanOrEqualTo(Long value) {
            addCriterion("UserDetails.d_id >=", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdLessThan(Long value) {
            addCriterion("UserDetails.d_id <", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdLessThanOrEqualTo(Long value) {
            addCriterion("UserDetails.d_id <=", value, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdIn(List<Long> values) {
            addCriterion("UserDetails.d_id in", values, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotIn(List<Long> values) {
            addCriterion("UserDetails.d_id not in", values, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdBetween(Long value1, Long value2) {
            addCriterion("UserDetails.d_id between", value1, value2, "dId");
            return (Criteria) this;
        }

        public Criteria andDIdNotBetween(Long value1, Long value2) {
            addCriterion("UserDetails.d_id not between", value1, value2, "dId");
            return (Criteria) this;
        }

        public Criteria andDMidIsNull() {
            addCriterion("UserDetails.d_mid is null");
            return (Criteria) this;
        }

        public Criteria andDMidIsNotNull() {
            addCriterion("UserDetails.d_mid is not null");
            return (Criteria) this;
        }

        public Criteria andDMidEqualTo(Long value) {
            addCriterion("UserDetails.d_mid =", value, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidNotEqualTo(Long value) {
            addCriterion("UserDetails.d_mid <>", value, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidGreaterThan(Long value) {
            addCriterion("UserDetails.d_mid >", value, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidGreaterThanOrEqualTo(Long value) {
            addCriterion("UserDetails.d_mid >=", value, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidLessThan(Long value) {
            addCriterion("UserDetails.d_mid <", value, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidLessThanOrEqualTo(Long value) {
            addCriterion("UserDetails.d_mid <=", value, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidIn(List<Long> values) {
            addCriterion("UserDetails.d_mid in", values, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidNotIn(List<Long> values) {
            addCriterion("UserDetails.d_mid not in", values, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidBetween(Long value1, Long value2) {
            addCriterion("UserDetails.d_mid between", value1, value2, "dMid");
            return (Criteria) this;
        }

        public Criteria andDMidNotBetween(Long value1, Long value2) {
            addCriterion("UserDetails.d_mid not between", value1, value2, "dMid");
            return (Criteria) this;
        }

        public Criteria andDAccountIsNull() {
            addCriterion("UserDetails.d_account is null");
            return (Criteria) this;
        }

        public Criteria andDAccountIsNotNull() {
            addCriterion("UserDetails.d_account is not null");
            return (Criteria) this;
        }

        public Criteria andDAccountEqualTo(String value) {
            addCriterion("UserDetails.d_account =", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountNotEqualTo(String value) {
            addCriterion("UserDetails.d_account <>", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountGreaterThan(String value) {
            addCriterion("UserDetails.d_account >", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountGreaterThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_account >=", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountLessThan(String value) {
            addCriterion("UserDetails.d_account <", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountLessThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_account <=", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountLike(String value) {
            addCriterion("UserDetails.d_account like", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountNotLike(String value) {
            addCriterion("UserDetails.d_account not like", value, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountIn(List<String> values) {
            addCriterion("UserDetails.d_account in", values, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountNotIn(List<String> values) {
            addCriterion("UserDetails.d_account not in", values, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountBetween(String value1, String value2) {
            addCriterion("UserDetails.d_account between", value1, value2, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDAccountNotBetween(String value1, String value2) {
            addCriterion("UserDetails.d_account not between", value1, value2, "dAccount");
            return (Criteria) this;
        }

        public Criteria andDNameIsNull() {
            addCriterion("UserDetails.d_name is null");
            return (Criteria) this;
        }

        public Criteria andDNameIsNotNull() {
            addCriterion("UserDetails.d_name is not null");
            return (Criteria) this;
        }

        public Criteria andDNameEqualTo(String value) {
            addCriterion("UserDetails.d_name =", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameNotEqualTo(String value) {
            addCriterion("UserDetails.d_name <>", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameGreaterThan(String value) {
            addCriterion("UserDetails.d_name >", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameGreaterThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_name >=", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameLessThan(String value) {
            addCriterion("UserDetails.d_name <", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameLessThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_name <=", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameLike(String value) {
            addCriterion("UserDetails.d_name like", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameNotLike(String value) {
            addCriterion("UserDetails.d_name not like", value, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameIn(List<String> values) {
            addCriterion("UserDetails.d_name in", values, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameNotIn(List<String> values) {
            addCriterion("UserDetails.d_name not in", values, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameBetween(String value1, String value2) {
            addCriterion("UserDetails.d_name between", value1, value2, "dName");
            return (Criteria) this;
        }

        public Criteria andDNameNotBetween(String value1, String value2) {
            addCriterion("UserDetails.d_name not between", value1, value2, "dName");
            return (Criteria) this;
        }

        public Criteria andDPasswordIsNull() {
            addCriterion("UserDetails.d_password is null");
            return (Criteria) this;
        }

        public Criteria andDPasswordIsNotNull() {
            addCriterion("UserDetails.d_password is not null");
            return (Criteria) this;
        }

        public Criteria andDPasswordEqualTo(String value) {
            addCriterion("UserDetails.d_password =", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordNotEqualTo(String value) {
            addCriterion("UserDetails.d_password <>", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordGreaterThan(String value) {
            addCriterion("UserDetails.d_password >", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_password >=", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordLessThan(String value) {
            addCriterion("UserDetails.d_password <", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordLessThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_password <=", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordLike(String value) {
            addCriterion("UserDetails.d_password like", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordNotLike(String value) {
            addCriterion("UserDetails.d_password not like", value, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordIn(List<String> values) {
            addCriterion("UserDetails.d_password in", values, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordNotIn(List<String> values) {
            addCriterion("UserDetails.d_password not in", values, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordBetween(String value1, String value2) {
            addCriterion("UserDetails.d_password between", value1, value2, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDPasswordNotBetween(String value1, String value2) {
            addCriterion("UserDetails.d_password not between", value1, value2, "dPassword");
            return (Criteria) this;
        }

        public Criteria andDSaltIsNull() {
            addCriterion("UserDetails.d_salt is null");
            return (Criteria) this;
        }

        public Criteria andDSaltIsNotNull() {
            addCriterion("UserDetails.d_salt is not null");
            return (Criteria) this;
        }

        public Criteria andDSaltEqualTo(String value) {
            addCriterion("UserDetails.d_salt =", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltNotEqualTo(String value) {
            addCriterion("UserDetails.d_salt <>", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltGreaterThan(String value) {
            addCriterion("UserDetails.d_salt >", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltGreaterThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_salt >=", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltLessThan(String value) {
            addCriterion("UserDetails.d_salt <", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltLessThanOrEqualTo(String value) {
            addCriterion("UserDetails.d_salt <=", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltLike(String value) {
            addCriterion("UserDetails.d_salt like", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltNotLike(String value) {
            addCriterion("UserDetails.d_salt not like", value, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltIn(List<String> values) {
            addCriterion("UserDetails.d_salt in", values, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltNotIn(List<String> values) {
            addCriterion("UserDetails.d_salt not in", values, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltBetween(String value1, String value2) {
            addCriterion("UserDetails.d_salt between", value1, value2, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDSaltNotBetween(String value1, String value2) {
            addCriterion("UserDetails.d_salt not between", value1, value2, "dSalt");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeIsNull() {
            addCriterion("UserDetails.d_create_time is null");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeIsNotNull() {
            addCriterion("UserDetails.d_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeEqualTo(Date value) {
            addCriterion("UserDetails.d_create_time =", value, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeNotEqualTo(Date value) {
            addCriterion("UserDetails.d_create_time <>", value, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeGreaterThan(Date value) {
            addCriterion("UserDetails.d_create_time >", value, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UserDetails.d_create_time >=", value, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeLessThan(Date value) {
            addCriterion("UserDetails.d_create_time <", value, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UserDetails.d_create_time <=", value, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeIn(List<Date> values) {
            addCriterion("UserDetails.d_create_time in", values, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeNotIn(List<Date> values) {
            addCriterion("UserDetails.d_create_time not in", values, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeBetween(Date value1, Date value2) {
            addCriterion("UserDetails.d_create_time between", value1, value2, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UserDetails.d_create_time not between", value1, value2, "dCreateTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeIsNull() {
            addCriterion("UserDetails.d_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeIsNotNull() {
            addCriterion("UserDetails.d_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeEqualTo(Date value) {
            addCriterion("UserDetails.d_modify_time =", value, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeNotEqualTo(Date value) {
            addCriterion("UserDetails.d_modify_time <>", value, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeGreaterThan(Date value) {
            addCriterion("UserDetails.d_modify_time >", value, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UserDetails.d_modify_time >=", value, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeLessThan(Date value) {
            addCriterion("UserDetails.d_modify_time <", value, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("UserDetails.d_modify_time <=", value, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeIn(List<Date> values) {
            addCriterion("UserDetails.d_modify_time in", values, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeNotIn(List<Date> values) {
            addCriterion("UserDetails.d_modify_time not in", values, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeBetween(Date value1, Date value2) {
            addCriterion("UserDetails.d_modify_time between", value1, value2, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("UserDetails.d_modify_time not between", value1, value2, "dModifyTime");
            return (Criteria) this;
        }

        public Criteria andDTsIsNull() {
            addCriterion("UserDetails.d_ts is null");
            return (Criteria) this;
        }

        public Criteria andDTsIsNotNull() {
            addCriterion("UserDetails.d_ts is not null");
            return (Criteria) this;
        }

        public Criteria andDTsEqualTo(Date value) {
            addCriterion("UserDetails.d_ts =", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsNotEqualTo(Date value) {
            addCriterion("UserDetails.d_ts <>", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsGreaterThan(Date value) {
            addCriterion("UserDetails.d_ts >", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsGreaterThanOrEqualTo(Date value) {
            addCriterion("UserDetails.d_ts >=", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsLessThan(Date value) {
            addCriterion("UserDetails.d_ts <", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsLessThanOrEqualTo(Date value) {
            addCriterion("UserDetails.d_ts <=", value, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsIn(List<Date> values) {
            addCriterion("UserDetails.d_ts in", values, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsNotIn(List<Date> values) {
            addCriterion("UserDetails.d_ts not in", values, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsBetween(Date value1, Date value2) {
            addCriterion("UserDetails.d_ts between", value1, value2, "dTs");
            return (Criteria) this;
        }

        public Criteria andDTsNotBetween(Date value1, Date value2) {
            addCriterion("UserDetails.d_ts not between", value1, value2, "dTs");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}