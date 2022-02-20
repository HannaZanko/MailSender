package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User implements Comparable<User> {

    private String userName;
    private String timeToSpend;
    private String activity;

    @Override
    public int compareTo(User o) {
        return this.userName.compareTo(o.getUserName());
    }
}
