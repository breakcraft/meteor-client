package net.runelite.api.packets;

public interface ClientPacket
{
int getId();
int getLength();

void setLength(int length);
ClientPacket OPPLAYERT();
ClientPacket EVENT_KEYBOARD();
ClientPacket OPHELDT();
ClientPacket OPLOCU();
ClientPacket OPOBJ4();
ClientPacket IF_BUTTON1();
ClientPacket OPNPCT();
ClientPacket IF_BUTTONT();
ClientPacket OPLOC1();
ClientPacket OPLOCT();
ClientPacket OPPLAYER6();
ClientPacket OPNPC5();
ClientPacket OPNPC2();
ClientPacket IF_BUTTON3();
ClientPacket IF1_BUTTON4();
ClientPacket IF_BUTTON5();
ClientPacket IF_BUTTON8();
ClientPacket IF_BUTTON10();
ClientPacket IF_BUTTON7();
ClientPacket OPOBJ3();
ClientPacket OPNPC3();
ClientPacket OPNPCU();
ClientPacket OPLOC4();
ClientPacket OPPLAYER7();
ClientPacket OPPLAYER5();
ClientPacket OPPLAYER1();
ClientPacket OPOBJU();
ClientPacket OPHELD3();
ClientPacket OPPLAYER4();
ClientPacket OPHELD1();
ClientPacket IF_BUTTON6();
ClientPacket OPOBJ2();
ClientPacket OPHELD2();
ClientPacket OPNPC4();
ClientPacket OPHELD4();
ClientPacket OPHELD5();
ClientPacket EVENT_MOUSE_CLICK();
ClientPacket OPLOC2();
ClientPacket OPLOC6();
ClientPacket IF1_BUTTON5();
ClientPacket BUTTON_CLICK();
ClientPacket IF_BUTTON9();
ClientPacket IF1_BUTTON3();
ClientPacket OPOBJ1();
ClientPacket OPLOC5();
ClientPacket OPPLAYER8();
ClientPacket IF_BUTTON4();
ClientPacket OPPLAYERU();
ClientPacket OPHELDU();
ClientPacket OPOBJ6();
ClientPacket IF1_BUTTON2();
ClientPacket OPPLAYER3();
ClientPacket OPLOC3();
ClientPacket OPNPC1();
ClientPacket IF1_BUTTON1();
ClientPacket MOVE_GAMECLICK();
ClientPacket REFLECTION_CHECK_REPLY();
ClientPacket LOGIN_STATISTICS();
ClientPacket IF_BUTTON2();
ClientPacket OPPLAYER2();
ClientPacket OPNPC6();
ClientPacket OPOBJT();

    boolean isOverride();

    void setOverride(boolean isOverride);
}