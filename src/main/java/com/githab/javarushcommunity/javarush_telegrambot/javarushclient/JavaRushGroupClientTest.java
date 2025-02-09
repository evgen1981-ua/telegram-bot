package com.githab.javarushcommunity.javarush_telegrambot.javarushclient;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.*;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupInfoType.TECH;
@DisplayName("Integration-level testing for JavaRushGroupClientImplTest")
public class JavaRushGroupClientTest {
    private final JavaRushGroupClient groupClient=new JavaRushGroupClientIml("https://javarush.com/api/1.0/rest");
@Test
    public void shouldProperlyGetGroupsWithEmptyArgs(){

        GroupRequestArgs args=GroupRequestArgs.builder().build();
        List<GroupInfo> groupInfoList=groupClient.getGroupList(args);
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertFalse(groupInfoList.isEmpty());
    }
@Test
public void shouldProperlyGetWithOffSetAndLimit(){
        GroupRequestArgs args=GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        List<GroupInfo> groupInfoList=groupClient.getGroupList(args);

        Assertions.assertNotNull(groupInfoList);
    Assertions.assertEquals(3,groupInfoList.size());
}
    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {

        GroupRequestArgs args = GroupRequestArgs.builder().build();


        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionInfo(args);


        Assertions.assertNotNull(groupList);
        Assertions.assertFalse(groupList.isEmpty());
    }
    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {

        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();


        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionInfo(args);


        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {

        GroupCountRequestArgs args = GroupCountRequestArgs.builder().build();


        Integer groupCount = groupClient.getGroupCount(args);


        Assertions.assertEquals(30, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupTECHCount() {

        GroupCountRequestArgs args = GroupCountRequestArgs.builder()
                .type(TECH)
                .build();


        Integer groupCount = groupClient.getGroupCount(args);


        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {

        Integer androidGroupId = 16;


        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);


        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(TECH, groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }


}
