package com.coopwell.smartclient.mode;

public class ACInfo
{
  private byte id;
  private byte nCtrl;
  private byte nHalt;
  private byte nSeasonMode;
  private byte nSetTemp;
  private byte nSpeed;
  private byte nState;
  private byte nTempDate;
  private byte nValild;
  private byte nWorkState;
  private String name;

  public byte getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public byte getnCtrl()
  {
    return this.nCtrl;
  }

  public byte getnHalt()
  {
    return this.nHalt;
  }

  public byte getnSeasonMode()
  {
    return this.nSeasonMode;
  }

  public byte getnSetTemp()
  {
    return this.nSetTemp;
  }

  public byte getnSpeed()
  {
    return this.nSpeed;
  }

  public byte getnState()
  {
    return this.nState;
  }

  public byte getnTempDate()
  {
    return this.nTempDate;
  }

  public byte getnValild()
  {
    return this.nValild;
  }

  public byte getnWorkState()
  {
    return this.nWorkState;
  }

  public void setId(byte paramByte)
  {
    this.id = paramByte;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setnCtrl(byte paramByte)
  {
    this.nCtrl = paramByte;
  }

  public void setnHalt(byte paramByte)
  {
    this.nHalt = paramByte;
  }

  public void setnSeasonMode(byte paramByte)
  {
    this.nSeasonMode = paramByte;
  }

  public void setnSetTemp(byte paramByte)
  {
    this.nSetTemp = paramByte;
  }

  public void setnSpeed(byte paramByte)
  {
    this.nSpeed = paramByte;
  }

  public void setnState(byte paramByte)
  {
    this.nState = paramByte;
  }

  public void setnTempDate(byte paramByte)
  {
    this.nTempDate = paramByte;
  }

  public void setnValild(byte paramByte)
  {
    this.nValild = paramByte;
  }

  public void setnWorkState(byte paramByte)
  {
    this.nWorkState = paramByte;
  }
}