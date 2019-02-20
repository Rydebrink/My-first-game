package com.Rydebrink.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.Rydebrink.Objects.TimeBlock;

public class TimeListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		TimeBlock.blockTimer++;
		System.out.println(TimeBlock.blockTimer);
		if(TimeBlock.blockTimer>=TimeBlock.time){
			TimeBlock.resetBlock();
		}
	}

}
