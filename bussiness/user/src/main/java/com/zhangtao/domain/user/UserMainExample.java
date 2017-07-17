package com.zhangtao.domain.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserMainExample() {
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

        public Criteria andMIdIsNull() {
            addCriterion("UserMain.m_id is null");
            return (Criteria) this;
        }

        public Criteria andMIdIsNotNull() {
            addCriterion("UserMain.m_id is not null");
            return (Criteria) this;
        }

        public Criteria andMIdEqualTo(Long value) {
            addCriterion("UserMain.m_id =", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotEqualTo(Long value) {
            addCriterion("UserMain.m_id <>", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThan(Long value) {
            addCriterion("UserMain.m_id >", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdGreaterThanOrEqualTo(Long value) {
            addCriterion("UserMain.m_id >=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThan(Long value) {
            addCriterion("UserMain.m_id <", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdLessThanOrEqualTo(Long value) {
            addCriterion("UserMain.m_id <=", value, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdIn(List<Long> values) {
            addCriterion("UserMain.m_id in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotIn(List<Long> values) {
            addCriterion("UserMain.m_id not in", values, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdBetween(Long value1, Long value2) {
            addCriterion("UserMain.m_id between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMIdNotBetween(Long value1, Long value2) {
            addCriterion("UserMain.m_id not between", value1, value2, "mId");
            return (Criteria) this;
        }

        public Criteria andMFlagIsNull() {
            addCriterion("UserMain.m_flag is null");
            return (Criteria) this;
        }

        public Criteria andMFlagIsNotNull() {
            addCriterion("UserMain.m_flag is not null");
            return (Criteria) this;
        }

        public Criteria andMFlagEqualTo(Integer value) {
            addCriterion("UserMain.m_flag =", value, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagNotEqualTo(Integer value) {
            addCriterion("UserMain.m_flag <>", value, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagGreaterThan(Integer value) {
            addCriterion("UserMain.m_flag >", value, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserMain.m_flag >=", value, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagLessThan(Integer value) {
            addCriterion("UserMain.m_flag <", value, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagLessThanOrEqualTo(Integer value) {
            addCriterion("UserMain.m_flag <=", value, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagIn(List<Integer> values) {
            addCriterion("UserMain.m_flag in", values, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagNotIn(List<Integer> values) {
            addCriterion("UserMain.m_flag not in", values, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagBetween(Integer value1, Integer value2) {
            addCriterion("UserMain.m_flag between", value1, value2, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("UserMain.m_flag not between", value1, value2, "mFlag");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginIsNull() {
            addCriterion("UserMain.m_multi_login is null");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginIsNotNull() {
            addCriterion("UserMain.m_multi_login is not null");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginEqualTo(Integer value) {
            addCriterion("UserMain.m_multi_login =", value, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginNotEqualTo(Integer value) {
            addCriterion("UserMain.m_multi_login <>", value, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginGreaterThan(Integer value) {
            addCriterion("UserMain.m_multi_login >", value, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginGreaterThanOrEqualTo(Integer value) {
            addCriterion("UserMain.m_multi_login >=", value, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginLessThan(Integer value) {
            addCriterion("UserMain.m_multi_login <", value, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginLessThanOrEqualTo(Integer value) {
            addCriterion("UserMain.m_multi_login <=", value, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginIn(List<Integer> values) {
            addCriterion("UserMain.m_multi_login in", values, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginNotIn(List<Integer> values) {
            addCriterion("UserMain.m_multi_login not in", values, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginBetween(Integer value1, Integer value2) {
            addCriterion("UserMain.m_multi_login between", value1, value2, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMMultiLoginNotBetween(Integer value1, Integer value2) {
            addCriterion("UserMain.m_multi_login not between", value1, value2, "mMultiLogin");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeIsNull() {
            addCriterion("UserMain.m_create_time is null");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeIsNotNull() {
            addCriterion("UserMain.m_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeEqualTo(Date value) {
            addCriterion("UserMain.m_create_time =", value, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeNotEqualTo(Date value) {
            addCriterion("UserMain.m_create_time <>", value, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeGreaterThan(Date value) {
            addCriterion("UserMain.m_create_time >", value, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UserMain.m_create_time >=", value, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeLessThan(Date value) {
            addCriterion("UserMain.m_create_time <", value, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UserMain.m_create_time <=", value, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeIn(List<Date> values) {
            addCriterion("UserMain.m_create_time in", values, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeNotIn(List<Date> values) {
            addCriterion("UserMain.m_create_time not in", values, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeBetween(Date value1, Date value2) {
            addCriterion("UserMain.m_create_time between", value1, value2, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UserMain.m_create_time not between", value1, value2, "mCreateTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeIsNull() {
            addCriterion("UserMain.m_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeIsNotNull() {
            addCriterion("UserMain.m_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeEqualTo(Date value) {
            addCriterion("UserMain.m_modify_time =", value, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeNotEqualTo(Date value) {
            addCriterion("UserMain.m_modify_time <>", value, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeGreaterThan(Date value) {
            addCriterion("UserMain.m_modify_time >", value, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UserMain.m_modify_time >=", value, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeLessThan(Date value) {
            addCriterion("UserMain.m_modify_time <", value, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("UserMain.m_modify_time <=", value, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeIn(List<Date> values) {
            addCriterion("UserMain.m_modify_time in", values, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeNotIn(List<Date> values) {
            addCriterion("UserMain.m_modify_time not in", values, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeBetween(Date value1, Date value2) {
            addCriterion("UserMain.m_modify_time between", value1, value2, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("UserMain.m_modify_time not between", value1, value2, "mModifyTime");
            return (Criteria) this;
        }

        public Criteria andMTsIsNull() {
            addCriterion("UserMain.m_ts is null");
            return (Criteria) this;
        }

        public Criteria andMTsIsNotNull() {
            addCriterion("UserMain.m_ts is not null");
            return (Criteria) this;
        }

        public Criteria andMTsEqualTo(Date value) {
            addCriterion("UserMain.m_ts =", value, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsNotEqualTo(Date value) {
            addCriterion("UserMain.m_ts <>", value, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsGreaterThan(Date value) {
            addCriterion("UserMain.m_ts >", value, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsGreaterThanOrEqualTo(Date value) {
            addCriterion("UserMain.m_ts >=", value, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsLessThan(Date value) {
            addCriterion("UserMain.m_ts <", value, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsLessThanOrEqualTo(Date value) {
            addCriterion("UserMain.m_ts <=", value, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsIn(List<Date> values) {
            addCriterion("UserMain.m_ts in", values, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsNotIn(List<Date> values) {
            addCriterion("UserMain.m_ts not in", values, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsBetween(Date value1, Date value2) {
            addCriterion("UserMain.m_ts between", value1, value2, "mTs");
            return (Criteria) this;
        }

        public Criteria andMTsNotBetween(Date value1, Date value2) {
            addCriterion("UserMain.m_ts not between", value1, value2, "mTs");
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