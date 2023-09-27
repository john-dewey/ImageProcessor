package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;


class CompositeHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public CompositeHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}

	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildComposite();
	}
}

class InterpolationHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public InterpolationHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}

	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildPolation();
	
	}
}

class BlurHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;

	public BlurHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
	}

	@Override
	public void handle(ActionEvent arg0) {
		_pic.doBlur();
		_momento.addMomento(_pic.copy());
	}
}

class GaussianHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public GaussianHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}

	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildGaussian();
	}
}

class EdgeHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;

	public EdgeHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
	}

	@Override
	public void handle(ActionEvent arg0) {
		_pic.doEdge();
		_momento.addMomento(_pic.copy());
	}
}

class ChannelHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public ChannelHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}

	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildAdjustChannel();
	}
}

class BrightenHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public BrightenHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}

	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildAdjustBrightness();
	}
}

class NoiseHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public NoiseHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}

	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildNoise();
	}
}

class ThresholdHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public ThresholdHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}


	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildThreshold();
	}
}
class GreyscaleHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;

	public GreyscaleHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
	}


	@Override
	public void handle(ActionEvent arg0) {
		_pic.doGreyscale();
		_momento.addMomento(_pic.copy());
	}
}
class InputFileHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;
	protected Label _fileName;

	public InputFileHandler(Picture pic, Momento momento, Label fileName) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
		_fileName = fileName;
	}


	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildImport(_fileName);

	}
}
class OutputFileHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public OutputFileHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}


	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildExport();
	}
}
class UndoHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;

	public UndoHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
	}


	@Override
	public void handle(ActionEvent arg0) {
		Picture lastVersion = _momento.undo();
		_pic.setPicture(lastVersion);
	}
}
class RedoHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;

	public RedoHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
	}


	@Override
	public void handle(ActionEvent arg0) {
		Picture restoreVersion = _momento.redo();
		_pic.setPicture(restoreVersion);
	}
}
class ScaleHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public ScaleHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}


	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildScale();
		
	}
}
class RotateHandler implements EventHandler<ActionEvent> {

	protected Picture _pic;
	protected Momento _momento;
	protected PopupManager _popupManager;

	public RotateHandler(Picture pic, Momento momento) {
		_pic = pic;
		_momento = momento;
		_popupManager = new PopupManager(_pic, _momento);
	}


	@Override
	public void handle(ActionEvent arg0) {
		_popupManager.buildRotate();
	}
}
