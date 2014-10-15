package hanto.studentkcbtsb.tournament;

public class TournamentTestPlayer extends HantoPlayer {
	
	public boolean testLineupChecker(){
		boolean isEmpty = true;
		for (int i = 0; i < myPlayer.getPiecesRemaining().size(); i++){
			myPlayer.removePieceFromLineup(myPlayer.getPiecesRemaining().get(i));
		}
		isEmpty = arePiecesLeftInLineup();
		return isEmpty;
	}
}
