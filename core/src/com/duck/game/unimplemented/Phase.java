package com.duck.game.unimplemented;
public class Phase {

  public Integer PhaseNumber;

  private Integer TimeLeft;

  private Integer StartTime;

  public Phase(Integer PhaseNumber, Integer StartTime){
      this.PhaseNumber = PhaseNumber;
      this.StartTime = StartTime;
      this.TimeLeft = this.StartTime;
  }
  public void ReduceTime() {
	  this.TimeLeft = this.TimeLeft - 1;
  }

  public void ResetTime() {
    TimeLeft = StartTime;
  }
  
  public int GetTime(){
	  return this.TimeLeft;
  } 

}
