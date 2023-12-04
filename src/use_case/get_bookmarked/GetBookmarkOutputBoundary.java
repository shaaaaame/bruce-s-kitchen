package use_case.get_bookmarked;


public interface GetBookmarkOutputBoundary {
    public void prepareSuccessView(GetBookmarkedOutputData getBookmarkedOutputData);
    public void prepareFailView(String error);
}
