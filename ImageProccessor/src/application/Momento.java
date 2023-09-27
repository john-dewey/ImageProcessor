package application;

/**
 * Momento object for storing and restoring edited pictures
 * @author Johnathan Dewey
 * @version September 13th 2023
 */

import java.util.Stack;

public class Momento {
	Stack<Picture> _pictures, _discard;
	
	public Momento() {
		_pictures = new Stack<Picture>();
		_discard = new Stack<Picture>();
	}
	
	private void saveMomento(Picture pic) {
		_pictures.push(pic);
		
	}
	
	private Picture getMomento() {
		Picture returnPicture = _pictures.pop();
		
		return returnPicture.copy();
	}
	
	private void saveDiscard(Picture pic) {
		_discard.push(pic);
		
	}
	
	private Picture getDiscard() {
		Picture returnPicture = _discard.pop();

		return returnPicture.copy();
	}
	
	private Picture top() {
		return _pictures.get(_pictures.size() - 1);
	}
	
	private boolean canPicturesPop() {
		if (_pictures.size() <= 1) {
			return false;
		}
		return true;
	}
	
	private void clearDiscard() {
		_discard = new Stack<Picture>();
	}
	
	private boolean canDiscardPop() {
		if (_discard.size() <= 0) {
			return false;
		}
		return true;
	}
	
	public void addMomento(Picture picture) {
		clearDiscard();
		saveMomento(picture);
		
	}

	public Picture undo() {
		if (canPicturesPop() == false) {
			return this.top(); 
		}
		
		Picture transfer = this.getMomento();
		this.saveDiscard(transfer);
		return this.top();
	}

	public Picture redo() {
		
		if (canDiscardPop() == false) {
			return this.top(); 
		}
		
		Picture transfer = this.getDiscard();
		this.saveMomento(transfer);
		return this.top();
	}
}
