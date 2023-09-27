package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

class Methods {
	public static void close(ActionEvent arg0) {
		((Stage) ((Node) arg0.getSource()).getScene().getWindow()).close();
	}
}

class ExitHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent arg0) {
		Methods.close(arg0);
	}
}

class FinishCompositeHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _path1;
	protected TextField _path2;

	public FinishCompositeHandler(Picture pic, Momento momento, TextField path1, TextField path2) {
		_pic = pic;
		_momento = momento;
		_path1 = path1;
		_path2 = path2;

	}

	@Override
	public void handle(ActionEvent arg0) {

		_pic.doComposite(new Picture(_path1.getText()), new Picture(_path2.getText()));
		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}

class FinishPolationHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _path;
	protected TextField _text;
	

	public FinishPolationHandler(Picture pic, Momento momento, TextField path, TextField text) {
		_pic = pic;
		_momento = momento;
		_path = path;
		_text = text;
		

	}

	@Override
	public void handle(ActionEvent arg0) {

		Double alpha = Constants.MATH_ALPHA_DEFAULT;
		try {
			alpha = Double.valueOf(_text.getText());
		} catch (NumberFormatException e) {
		}

		_pic.doPolation(new Picture(_path.getText()), alpha);
		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}

class FinishGaussianHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _sigmaText;

	public FinishGaussianHandler(Picture pic, Momento momento, TextField sigmaText) {
		_pic = pic;
		_momento = momento;
		_sigmaText = sigmaText;
	}

	@Override
	public void handle(ActionEvent arg0) {

		double sigma = 0;
		try {
			sigma = Double.valueOf(_sigmaText.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		_pic.doGaussian(sigma);
		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}

class FinishChannelHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _channelValue;

	public FinishChannelHandler(Picture pic, Momento momento, TextField channelValue) {
		_pic = pic;
		_momento = momento;
		_channelValue = channelValue;
	}

	@Override
	public void handle(ActionEvent arg0) {

		int level = 0;
		try {
			level = Integer.valueOf(_channelValue.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		_pic.doQuantization(level);

		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}



class FinishBrightnessHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected ComboBox<String> _box;

	public FinishBrightnessHandler(Picture pic, Momento momento, ComboBox<String> box) {
		_pic = pic;
		_momento = momento;
		_box = box;
	}

	@Override
	public void handle(ActionEvent arg0) {
		String choice = _box.getValue().toString();
		String brighten = "Brighten";
		String darken = "Darken";

		if (choice.equals(brighten)) {
			_pic.doBrighten(Constants.MATH_ALPHA_DEFAULT);
			_momento.addMomento(_pic.copy());
		}
		else if(choice.equals(darken)) {
			_pic.doDarken(Constants.MATH_ALPHA_DEFAULT);
			_momento.addMomento(_pic.copy());
		}

		Methods.close(arg0);
	}
}
class FinishNoiseHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _alphaInput;
	

	public FinishNoiseHandler(Picture pic, Momento momento, TextField alphaInput) {
		_pic = pic;
		_momento = momento;
		_alphaInput = alphaInput;
		
	}

	@Override
	public void handle(ActionEvent arg0) {

		Double alpha = Constants.MATH_ALPHA_DEFAULT;
		try {
			alpha = Double.valueOf(_alphaInput.getText());
		} catch (NumberFormatException e) {
		}


		_pic.doNoise(alpha);

		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}

class FinishThresholdHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _alphaInput;
	

	public FinishThresholdHandler(Picture pic, Momento momento, TextField alphaInput) {
		_pic = pic;
		_momento = momento;
		_alphaInput = alphaInput;
		
	}
	@Override
	public void handle(ActionEvent arg0) {

		Double threshold = Constants.MATH_ALPHA_DEFAULT;

		try {
			threshold = Double.valueOf(_alphaInput.getText());
		} catch (NumberFormatException e) {
		}


		_pic.doThreshold(threshold);
		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}

class FinishImportHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _path;
	protected Label _fileLabel;

	public FinishImportHandler(Picture pic, Momento momento, TextField alphaInput, Label fileLabel) {
		_pic = pic;
		_momento = momento;
		_path = alphaInput;
		_fileLabel = fileLabel;
	}

	@Override
	public void handle(ActionEvent arg0) {

		_pic.setPicture(new Picture(_path.getText()));
		_momento.addMomento(_pic.copy());

		for (int i = _path.getText().length() - 1; i > 0; i--) {
			if (_path.getText().charAt(i) == '/') {
				_fileLabel.setText(_path.getText().substring(i + 1));
				break;
			}
		}

		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}


class FinishExportHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _filename;

	public FinishExportHandler(Picture pic, Momento momento, TextField filename) {
		_pic = pic;
		_momento = momento;
		_filename= filename;
	}

	@Override
	public void handle(ActionEvent arg0) {

		_pic.export(_filename.getText());

		Methods.close(arg0);
	}
}

class FinishScaleHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _value;

	public FinishScaleHandler(Picture pic, Momento momento, TextField value) {
		_pic = pic;
		_momento = momento;
		_value = value;
	}
	
//	public ArrayList<Node> getAllNodes(Parent root) {
//	    ArrayList<Node> nodes = new ArrayList<Node>();
//	    addAllDescendents(root, nodes);
//	    return nodes;
//	}
//
//	private void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
//	    for (Node node : parent.getChildrenUnmodifiable()) {
//	        nodes.add(node);
//	        if (node instanceof Parent)
//	            addAllDescendents((Parent)node, nodes);
//	    }
//	}

	@Override
	public void handle(ActionEvent arg0) {
		double percent = 0.0;
		try {
			percent = Double.valueOf(_value.getText());
		} 
		catch (NumberFormatException e) {

		}

		//Stage superStage = ((Stage) ((Node) arg0.getSource()).getScene().getWindow());
//		Stage superStage = (Stage) _value.getScene().getWindow();
//		ArrayList<Node> nodes = getAllNodes(((Node) arg0.getSource()).getParent());
//		
//		
//		for (int i = 0; i < nodes.size(); i++) {
//			System.out.println(nodes.get(i).getId());
//		}
		
		
		_pic.doScale(percent);
		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}

class FinishRotateHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected TextField _angle;
	protected TextField _x;
	protected TextField _y;

	public FinishRotateHandler(Picture pic, Momento momento, TextField angle, TextField x, TextField y) {
		_pic = pic;
		_momento = momento;
		_angle = angle;
		_x = x;
		_y = y;
	}

	@Override
	public void handle(ActionEvent arg0) {
		double angle = 0.0;
		double x = 0.0;
		double y = 0.0;
		try {
			angle = Double.valueOf(_angle.getText());
			x = Double.valueOf(_x.getText());
			y = Double.valueOf(_y.getText());
		} 
		catch (NumberFormatException e) {
			
		}

		_pic.doRotate((int) x, (int) y, angle);
		_momento.addMomento(_pic.copy());

		Methods.close(arg0);
	}
}


