package com.base.vo;

public record QuestCompleteEvent(String userId, String questName, int rewardAmount) {
}
