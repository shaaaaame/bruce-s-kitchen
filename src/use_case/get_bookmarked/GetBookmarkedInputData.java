package use_case.get_bookmarked;

import java.util.UUID;

public class GetBookmarkedInputData {
    private String username;
    public GetBookmarkedInputData(String username) {
        this.username = username;
    }
    public String getUsername(){return this.username;}
}
