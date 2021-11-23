package UAW.type.units;

import UAW.ai.types.BomberJetAI;
import UAW.entities.abilities.*;
import UAW.graphics.UAWFxDynamic;
import arc.math.*;
import arc.util.Time;
import mindustry.gen.*;
import mindustry.type.UnitType;
import mindustry.world.meta.BlockFlag;

public class JetUnitType extends UnitType {
	public float trailX = 5f;
	public float trailY = 0f;
	public int trailLength = 15;
	public float trailWidth = 4f;
	/*
	public Trail trailLeft = new Trail(trailLength);
	public Trail trailRight = new Trail(trailLength);
	*/

	public JetUnitType(String name) {
		super(name);
		engineSize = 0;
		flying = true;
		lowAltitude = false;
		constructor = UnitEntity::create;
		circleTarget = true;
		defaultController = BomberJetAI::new;
		faceTarget = false;
		targetAir = false;
		playerTargetFlags = new BlockFlag[]{null};
	}

	@Override
	public void update(Unit unit) {
		super.update(unit);
		float cx = Angles.trnsx(unit.rotation - 90, trailX, trailY) + unit.x;
		float cy = Angles.trnsy(unit.rotation - 90, trailX, trailY) + unit.y;
		float cx2 = Angles.trnsx(unit.rotation - 90, -trailX, trailY) + unit.x;
		float cy2 = Angles.trnsy(unit.rotation - 90, -trailX, trailY) + unit.y;
		if (unit.moving()) {
			if (Mathf.chanceDelta(Time.delta)) {
				UAWFxDynamic.jetTrail(trailLength).at(cx, cy, trailWidth, unit.team.color);
				UAWFxDynamic.jetTrail(trailLength).at(cx2, cy2, trailWidth, unit.team.color);
			}
		}
		omniMovement = !unit.isPlayer() && unit.isShooting && unit.isAI();
		/*
		trailLeft.update(cx, cy);
		trailRight.update(cx2, cy2);*/
	}

/*
	@Override
	public void draw(Unit unit) {
		super.draw(unit);
		Draw.z(Layer.effect);
		trailLeft.draw(trailColor, trailWidth);
		trailRight.draw(trailColor, trailWidth);
	}*/
}
