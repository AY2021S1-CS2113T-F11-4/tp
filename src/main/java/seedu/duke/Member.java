package seedu.duke;

public class Member {
    String userId;

    public Member(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        /* Check if o is an instance of Member or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Member)) {
            return false;
        }
        Member v = (Member) o;
        // Compare the userId of members and return accordingly
        return userId.equals(v.userId);
    }

}


