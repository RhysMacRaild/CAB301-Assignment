package com.company;

public class MemberCollection {
    public Member[] members = new Member[10];
//    public String[] MembersIndex = new String[10];
    public int numOfMembersInArray = 0;


    public void addMember(Member newMember) {
        for (int index = 0; index < 10; index++){
            if (this.members[index] != null){
                this.members[index] = newMember;
            }
        }
        numOfMembersInArray++;
    }

    public void remove(Member memberToRemove) {
        int memberIndex= getMemberIndex(memberToRemove);

//        Check the index is valid
        if (memberIndex < 0){
            System.out.println("Member not in this collection");
            return;
        }
        this.members[memberIndex] = null;
        numOfMembersInArray--;
    }

    public Member returnMemberFromUsername(String memberToReturn){
        for (int index = 0; index<10; index++) {
            if (this.members[index].userName == memberToReturn) {
                return this.members[index];
            }
        }
        return null;
    }

    public int getMemberIndex(Member member) {
        for (int index = 0; index<10; index++) {
            if (this.members[index] == member) {
                return index;
            }
        }
        return -1;
    }

//    Remove all copies of a movie from all members (If staff removes movie from software)
    public void removeMovieFromAllMembersCollections(String movieToRemove){
        for (int memberIndex = 0; memberIndex < 10; memberIndex++){
            if (members[memberIndex] != null){
                this.members[memberIndex].borrowedMovies.removeMovieByString(movieToRemove);
            }
        }
    }
}
