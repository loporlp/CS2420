package assign02;

public class CS2420Student extends UofUStudent
{
	
	//fields
	private EmailAddress contactInfo;
	private double quizScore, assignScore, examScore, labScore = -1;
	private int quizCount, assignCount, examCount, labCount = 0;
	
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo)
	{
		super(firstName, lastName, uNID);
		this.contactInfo = contactInfo;
	}
	
	public EmailAddress getContactInfo()
	{
		return contactInfo;
	}
	public void addScore(double score, String category)
	{
		switch(category) 
		{
		case "assignment":
			if(assignScore == -1)
			{
			assignScore = 0;	
			}
			assignScore += score; 
			assignCount++;
			//System.out.println(assignScore + " A");
			break;
		case "exam":
			if(examScore == -1)
			{
			examScore = 0;	
			}
			examScore += score;
			
			examCount++;
			
			break;
		case "lab":
			if(labScore == -1)
			{
			labScore = 0;	
			}
			labScore += score;
			labCount++;
			break;
		case "quiz":
			if(quizScore == -1)
			{
			quizScore = 0;	
			}
			quizScore += score;
			quizCount++;
			break;
		}
	}
	/*
	 * computes the final score of the class as said in the syllabus
	 * if any scores are missing returns 0
	 */
	public double computeFinalScore() 
	{
		double finalScore = 0;
		//If any scores do not exist return 0
		if(quizCount == 0 || examCount == 0 ||
				labCount == 0 || assignCount == 0) {
			return 0.0;
		}
		// if exam score is less than 65% then exam score is final score
		if((examScore / examCount) < 65) {
			return examScore / examCount;
		}
		//add up scores
		finalScore += (assignScore / assignCount) * .4;

		finalScore +=(examScore / examCount) * .4;

		finalScore +=(quizScore / quizCount) * .1;

		finalScore += (labScore / labCount) * .1;

		return finalScore;
		
	}
	public String computeFinalGrade()
	{
		double finalScore = computeFinalScore();
		if(quizCount == 0 || examCount == 0 ||
				labCount == 0 || assignCount == 0) {
			return "N/A";
		}
		
		if(finalScore < 60) {
			return "E";
		}
		if(finalScore < 63) {
			return "D-";
		}
		if(finalScore < 67) {
			return "D";
		}
		if(finalScore < 70) {
			return "D+";
		}
		if(finalScore < 73) {
			return "C-";
		}
		if(finalScore < 77) {
			return "C";
		}
		if(finalScore < 80) {
			return "C+";
		}
		if(finalScore < 83) {
			return "B-";
		}
		if(finalScore < 87) {
			return "B";
		}
		if(finalScore < 90) {
			return "B+";
		}
		if(finalScore < 93) {
			return "A-";
		}
		return "A";
		
	}

}
