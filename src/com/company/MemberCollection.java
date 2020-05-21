package com.company;

public class MemberCollection {
    public Member[] Members = new Member[10];
//    public String[] MembersIndex = new String[10];
    public int NumOfMembersInArray = 0;


    public void addMember(Member newMember) {
        for (int index = 0; index < 10; index++){
            if (this.Members[index] != null){
                this.Members[index] = newMember;
            }
        }
        NumOfMembersInArray++;
    }

    public void remove(Member memberToRemove) {
        int memberIndex= getMemberIndex(memberToRemove);

//        Check the index is valid
        if (memberIndex < 0){
            System.out.println("Member not in this collection");
            return;
        }
        this.Members[memberIndex] = null;
        NumOfMembersInArray--;
    }

    public Member returnMemberFromUsername(String memberToReturn){
        for (int index = 0; index<10; index++) {
            if (this.Members[index].userName == memberToReturn) {
                return this.Members[index];
            }
        }
        return null;
    }

    public int getMemberIndex(Member member) {
        for (int index = 0; index<10; index++) {
            if (this.Members[index] == member) {
                return index;
            }
        }
        return -1;
    }
}
