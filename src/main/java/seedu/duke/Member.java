package seedu.duke;

public class Member {
    String userId;
    public Member(String userId){
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Member)) {
            return false;
        }
        Member v = (Member) o;
        // Compare the data members and return accordingly
        return userId.equals(v.userId);
    }
}
