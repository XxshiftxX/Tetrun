package com.mygdx.game.handlers;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Mino;
import javafx.scene.shape.MoveTo;

import java.util.ArrayList;
import java.util.Queue;

public class MinoQueue {

    private static final int QUEUE_SIZE = 5;            // Queue의 최대 크기
    private static final int MINO_TYPES = 7;            // MinoType의 개수
    private static final float PREVIEW_POINT_X = 30f;   // 미리보기 구역의 시작 X값
    private static final float PREVIEW_POINT_Y = 20f;   // 미리보기 구역의 시작 Y값
    private static final float TERM = 3.0f;             // 미리보기 구역의 Mino 사이의 간격
    private static final float NOW_MINO_POINT_X = 20f;  // 조작 가능한 현재 Mino의 시작 X값
    private static final float NOW_MINO_POINT_Y = 17f;  // 조각 가능한 현재 Mino의 시작 Y값
    private static final int FLOOR_MINO_AMOUNT = 10;

    private MinoContainer minoContainer = new MinoContainer();
    private Mino[] minoQueue = new Mino[QUEUE_SIZE];
    //private Mino[] minoFloor = new Mino[FLOOR_MINO_AMOUNT];
    ArrayList<Mino> minoFloor = new ArrayList<Mino>();
    private int minoDir = 0;
    // 현재 플레이어가 조종 할 수 있는 Mino.
    private Mino nowMino = new Mino();
    private int floorIndex = 0;

    public MinoQueue()
    {
//        for(int i = 0; i < FLOOR_MINO_AMOUNT; ++i)
//            minoFloor[i] = new Mino();
        for(int i = 0; i < QUEUE_SIZE; ++i)
            minoQueue[i] = new Mino();
    }

    // 맨 처음 시작 때 호출
    public void SetQueue() {
        DecideMino();
    }

    public void DecideMino()
    {
        for(int i = 0; i < QUEUE_SIZE; ++i) {
            DecideMinoAt(i);
        }
        ShiftQueue();
    }

    // Queue의 index번째 요소의 Mino가 가지는 모양을 결정
    public void DecideMinoAt(int index)
    {
        int minoNum = (int)(Math.random() * MINO_TYPES);
        minoQueue[index].SetMino(minoContainer.minoPattern[minoNum]);
        SetPreviewPoint(minoQueue[index], index);
    }

    public void Deploy()
    {
        nowMino.Deploy();
        MoveToFloor();
        ShiftQueue();
    }

    // 여기서 무언가 바닥 Mino가 깔리지 않는다.
    private void MoveToFloor()
    {
        minoFloor.add(new Mino(nowMino));
    }


    // 만일 플레이어가 Mino를 배치 시, 한칸씩 당김.
    // 이때, Queue의 0번째 위치의 Mino는 NowMino에 들어간다.
    public void ShiftQueue()
    {
        nowMino.SetMino(minoQueue[0].getShape());
        SetNowMinoPoint();
        nowMino.SetisPutted(false);
        for(int i = 1; i < QUEUE_SIZE; ++i)
        {
            minoQueue[i-1].SetMino(minoQueue[i].getShape());
        }
        int minoNum = (int)(Math.random() * MINO_TYPES);
        minoQueue[QUEUE_SIZE - 1].SetMino(minoContainer.minoPattern[minoNum]);
    }

    // Queue에 들어있는 Mino들의 미리보기를 위한 함수
    private void SetPreviewPoint(Mino mino, int index)
    {
        mino.SetPosition( (float)(PREVIEW_POINT_X + (index-1) * TERM), (float)PREVIEW_POINT_Y);
    }

    // 플레이어가 조작 가능한 Mino의 위치를 초기화
    private void SetNowMinoPoint()
    {
        nowMino.SetPosition(NOW_MINO_POINT_X, NOW_MINO_POINT_Y);
    }

    public Mino[] GetMinos()
    {
        return minoQueue;
    }

//    public Mino[] GetFloorMinos()
//    {
//        return minoFloor;
//    }


    public ArrayList<Mino> GetMinoFloor() {
        return minoFloor;
    }

    public Mino GetNowMino()
    {
        return nowMino;
    }

    public int GetFloorIndex()
    {
        return floorIndex;
    }
}
