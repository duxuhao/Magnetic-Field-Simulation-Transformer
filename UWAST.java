/*
 * UWAST.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Oct 7 2017, 21:59 by COMSOL 5.2.0.166. */
public class UWAST {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\xuhao.du.UWA\\Peter Du\\transformer\\FEM\\Comsol");

    model.label("UWAST.mph");

    model.comments("Untitled\n\n");

    model.param().set("R", "1.8[ohm]", "winding resistance");
    model.param().set("N", "400", "Number of turns");
    model.param().set("L", "31.72[mH]", "winding induction");
    model.param().set("nu", "50[Hz]", "Frequency of supply voltage");
    model.param().set("Vac", "40[V]", "Supply voltage");

    model.modelNode().create("comp1");

    model.geom().create("geom1", 3);

    model.mesh().create("mesh1", "geom1");

    model.geom("geom1").lengthUnit("mm");
    model.geom("geom1").create("wp1", "WorkPlane");
    model.geom("geom1").feature("wp1").set("unite", "on");
    model.geom("geom1").feature("wp1").set("quickplane", "xz");
    model.geom("geom1").feature("wp1").set("quicky", "10");
    model.geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"250", "500"});
    model.geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.geom("geom1").feature("wp1").geom().feature("r2").set("size", new String[]{"150", "400"});
    model.geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.geom("geom1").feature("wp1").geom().feature("r4").set("size", new String[]{"50", "200"});
    model.geom("geom1").feature("wp1").geom().feature("r4").set("pos", new String[]{"75", "-100"});
    model.geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.geom("geom1").feature("wp1").geom().feature("r3").set("size", new String[]{"50", "198"});
    model.geom("geom1").feature("wp1").geom().feature("r3").set("pos", new String[]{"75", "-100"});
    model.geom("geom1").feature("wp1").geom().create("co1", "Compose");
    model.geom("geom1").feature("wp1").geom().feature("co1").set("formula", "r1-r2-r4");
    model.geom("geom1").feature("wp1").geom().feature("co1").set("intbnd", false);
    model.geom("geom1").create("ext1", "Extrude");
    model.geom("geom1").feature("ext1").setIndex("distance", "50", 0);
    model.geom("geom1").feature("ext1").selection("input").set(new String[]{"wp1"});
    model.geom("geom1").create("wp2", "WorkPlane");
    model.geom("geom1").feature("wp2").set("unite", "on");
    model.geom("geom1").feature("wp2").set("quickz", "-100");
    model.geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", "4", 0);
    model.geom("geom1").feature("wp2").geom().feature("c1").set("layername", new String[]{"Layer 1"});
    model.geom("geom1").feature("wp2").geom().feature("c1").set("pos", new String[]{"-100", "-15"});
    model.geom("geom1").feature("wp2").geom().feature("c1").set("r", "42");
    model.geom("geom1").feature("wp2").geom().feature("c1").set("type", "curve");
    model.geom("geom1").create("ext2", "Extrude");
    model.geom("geom1").feature("ext2").setIndex("distance", "200", 0);
    model.geom("geom1").feature("ext2").selection("input").set(new String[]{"wp2"});
    model.geom("geom1").create("blk1", "Block");
    model.geom("geom1").feature("blk1").set("base", "center");
    model.geom("geom1").feature("blk1").set("size", new String[]{"500", "500", "1000"});
	model.geom("geom1").feature("wp1").geom().feature("r3").set("pos", new String[]{"remove", "-100"});

    model.param().set("remove", "105");
    model.param().descr("remove", "remove level");
    model.geom("geom1").run();
    model.geom("geom1").run("fin");

    model.selection().create("sel1", "Explicit");
    model.selection("sel1").set(new int[]{4});
    model.selection().create("sel2", "Explicit");
    model.selection("sel2").set(new int[]{7});
    model.selection().create("sel3", "Explicit");
    model.selection("sel3").set(new int[]{2, 3, 5, 6});
    model.selection().create("sel4", "Explicit");
    model.selection("sel4").set(new int[]{4, 7});
    model.selection().create("sel5", "Explicit");
    model.selection("sel5").geom("geom1", 2);
    model.selection("sel5").set(new int[]{7, 8, 9, 10, 11, 13, 14, 21, 22, 23, 24, 26, 27, 28, 29});
    model.selection("sel1").label("Main Core");
    model.selection("sel2").label("Removable Section");
    model.selection("sel3").label("Winding");
    model.selection("sel4").label("Core");
    model.selection("sel5").label("Winding Insulation");

    model.view("view1").hideEntities().create("hide1");
    model.view("view1").hideEntities("hide1").geom("geom1", 2);
    model.view("view1").hideEntities("hide1").set(new int[]{1, 2, 3, 4, 5, 47});

    model.material().create("mat1", "Common", "comp1");
    model.material().create("mat2", "Common", "comp1");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.material("mat2").selection().named("sel4");
    model.material("mat2").propertyGroup().create("BHCurve", "BH curve");
    model.material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.material("mat2").propertyGroup().create("HBCurve", "HB curve");
    model.material("mat2").propertyGroup("HBCurve").func().create("HB", "Interpolation");
    model.material("mat2").propertyGroup().create("EffectiveBHCurve", "Effective BH curve");
    model.material("mat2").propertyGroup("EffectiveBHCurve").func().create("BHeff", "Interpolation");
    model.material("mat2").propertyGroup().create("EffectiveHBCurve", "Effective HB curve");
    model.material("mat2").propertyGroup("EffectiveHBCurve").func().create("HBeff", "Interpolation");

    model.physics().create("mf", "InductionCurrents", "geom1");
    model.physics("mf").create("al2", "AmperesLaw", 3);
    model.physics("mf").feature("al2").selection().named("sel4");
    model.physics("mf").create("mtcd1", "MultiTurnCoilDomain", 3);
    model.physics("mf").feature("mtcd1").selection().named("sel3");
    model.physics("mf").feature("mtcd1").feature("ccc1").feature("ct1").selection().set(new int[]{33});
    model.physics("mf").create("gfa1", "GaugeFixingA", 3);
    model.physics().create("cir", "Circuit", "geom1");
    model.physics("cir").create("V1", "VoltageSource", -1);
    model.physics("cir").create("IvsU1", "ModelDeviceIV", -1);
    model.physics("cir").create("R1", "Resistor", -1);
    model.physics("cir").create("L1", "Inductor", -1);

    model.mesh("mesh1").create("ftet1", "FreeTet");
    model.mesh("mesh1").feature("ftet1").selection().geom("geom1");
    model.mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel3");

    model.view("view1").set("scenelight", "off");
    model.view("view2").axis().set("abstractviewxscale", "0.7649512887001038");
    model.view("view2").axis().set("ymin", "-293.7413024902344");
    model.view("view2").axis().set("xmax", "416.4048767089844");
    model.view("view2").axis().set("abstractviewyscale", "0.7649512887001038");
    model.view("view2").axis().set("abstractviewbratio", "-0.08748260140419006");
    model.view("view2").axis().set("abstractviewtratio", "0.08748260140419006");
    model.view("view2").axis().set("abstractviewrratio", "0.9335888624191284");
    model.view("view2").axis().set("xmin", "-383.7341613769531");
    model.view("view2").axis().set("abstractviewlratio", "-0.9240505695343018");
    model.view("view2").axis().set("ymax", "293.7413024902344");

    model.material("mat1").label("Air");
    model.material("mat1").set("family", "air");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("rho").set("expr", "pA*0.02897/8.314/T");
    model.material("mat1").propertyGroup("def").func("rho").set("dermethod", "manual");
    model.material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "0", "1"}, {"T", "0", "1"}});
    model.material("mat1").propertyGroup("def").func("rho")
         .set("argders", new String[][]{{"pA", "d(pA*0.02897/8.314/T,pA)"}, {"T", "d(pA*0.02897/8.314/T,T)"}});
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*287*T)");
    model.material("mat1").propertyGroup("def").func("cs").set("dermethod", "manual");
    model.material("mat1").propertyGroup("def").func("cs").set("plotargs", new String[][]{{"T", "0", "1"}});
    model.material("mat1").propertyGroup("def").func("cs")
         .set("argders", new String[][]{{"T", "d(sqrt(1.4*287*T),T)"}});
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T[1/K])[Pa*s]");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"10[S/m]", "0", "0", "0", "10[S/m]", "0", "0", "0", "10[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T[1/K])[J/(kg*K)]");
    model.material("mat1").propertyGroup("def").set("density", "rho(pA[1/Pa],T[1/K])[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T[1/K])[W/(m*K)]", "0", "0", "0", "k(T[1/K])[W/(m*K)]", "0", "0", "0", "k(T[1/K])[W/(m*K)]"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T[1/K])[m/s]");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material("mat1").propertyGroup("def").addInput("pressure");
    model.material("mat1").propertyGroup("RefractiveIndex").set("n", "");
    model.material("mat1").propertyGroup("RefractiveIndex").set("ki", "");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.material("mat2").label("Soft Iron (without losses)");
    model.material("mat2").set("family", "iron");
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"10[S/m]", "0", "0", "0", "10[S/m]", "0", "0", "0", "10[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2.0"}, 
         {"111408", "2.1"}, 
         {"175070", "2.2"}, 
         {"261469", "2.3"}, 
         {"318310", "2.4"}});
    model.material("mat2").propertyGroup("BHCurve").set("normB", "BH(normH[m/A])[T]");
    model.material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.material("mat2").propertyGroup("HBCurve").func("HB").set("extrap", "linear");
    model.material("mat2").propertyGroup("HBCurve").func("HB")
         .set("table", new String[][]{{"0", "0"}, 
         {"1", "663.146"}, 
         {"1.1", "1067.5"}, 
         {"1.2", "1705.23"}, 
         {"1.3", "2463.11"}, 
         {"1.4", "3841.67"}, 
         {"1.5", "5425.74"}, 
         {"1.6", "7957.75"}, 
         {"1.7", "12298.3"}, 
         {"1.8", "20462.8"}, 
         {"1.9", "32169.6"}, 
         {"2.0", "61213.4"}, 
         {"2.1", "111408"}, 
         {"2.2", "175070"}, 
         {"2.3", "261469"}, 
         {"2.4", "318310"}});
    model.material("mat2").propertyGroup("HBCurve").set("normH", "HB(normB[1/T])[A/m]");
    model.material("mat2").propertyGroup("HBCurve").addInput("magneticfluxdensity");
    model.material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("extrap", "linear");
    model.material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "0.5153194707974875"}, 
         {"1067.5", "0.8295360826670417"}, 
         {"1705.23", "1.3251052124087301"}, 
         {"2463.11", "1.914040862368166"}, 
         {"3841.67", "2.5792096804469185"}, 
         {"5425.74", "2.8333960472097166"}, 
         {"7957.75", "3.1289762765430496"}, 
         {"12298.3", "3.444978983847673"}, 
         {"20462.8", "3.7798414094378847"}, 
         {"32169.6", "4.0582907864558395"}, 
         {"61213.4", "4.420724736264278"}, 
         {"111408", "4.721733215122054"}, 
         {"175070", "4.941547514013738"}, 
         {"261469", "5.144423226476165"}, 
         {"318310", "5.253164131393496"}});
    model.material("mat2").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeff[m/A])[T]");
    model.material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.material("mat2").propertyGroup("EffectiveHBCurve").func("HBeff").set("extrap", "linear");
    model.material("mat2").propertyGroup("EffectiveHBCurve").func("HBeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"0.5153194707974875", "663.146"}, 
         {"0.8295360826670417", "1067.5"}, 
         {"1.3251052124087301", "1705.23"}, 
         {"1.914040862368166", "2463.11"}, 
         {"2.5792096804469185", "3841.67"}, 
         {"2.8333960472097166", "5425.74"}, 
         {"3.1289762765430496", "7957.75"}, 
         {"3.444978983847673", "12298.3"}, 
         {"3.7798414094378847", "20462.8"}, 
         {"4.0582907864558395", "32169.6"}, 
         {"4.420724736264278", "61213.4"}, 
         {"4.721733215122054", "111408"}, 
         {"4.941547514013738", "175070"}, 
         {"5.144423226476165", "261469"}, 
         {"5.253164131393496", "318310"}});
    model.material("mat2").propertyGroup("EffectiveHBCurve").set("normHeff", "HBeff(normBeff[1/T])[A/m]");
    model.material("mat2").propertyGroup("EffectiveHBCurve").addInput("magneticfluxdensity");
    model.material("mat2").propertyGroup("BHCurve").func("BH").set("source", "file");
	model.material("mat2").propertyGroup("BHCurve").func("BH")
         .set("filename", "D:\\xuhao.du.UWA\\Peter Du\\transformer\\FEM\\Comsol\\BH_curve.txt");
    model.material("mat2").propertyGroup("BHCurve").func("BH").importData();
    model.material("mat2").propertyGroup("HBCurve").func("HB").set("source", "file");
    model.material("mat2").propertyGroup("HBCurve").func("HB")
         .set("filename", "D:\\xuhao.du.UWA\\Peter Du\\transformer\\FEM\\Comsol\\HB_curve.txt");
    model.material("mat2").propertyGroup("HBCurve").func("HB").importData();
    model.material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("source", "file");
    model.material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("filename", "D:\\xuhao.du.UWA\\Peter Du\\transformer\\FEM\\Comsol\\SimpleBH.txt");
    model.material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").importData();
    model.material("mat2").propertyGroup("EffectiveHBCurve").func("HBeff").set("source", "file");
    model.material("mat2").propertyGroup("EffectiveHBCurve").func("HBeff")
         .set("filename", "D:\\xuhao.du.UWA\\Peter Du\\transformer\\FEM\\Comsol\\SimpleHB.txt");
    model.material("mat2").propertyGroup("EffectiveHBCurve").func("HBeff").importData();

    model.physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", "1");
    model.physics("mf").feature("al2").set("ConstitutiveRelationH", "HBCurve");
    model.physics("mf").feature("mtcd1").set("CoilType", "Numeric");
    model.physics("mf").feature("mtcd1").set("CoilExcitation", "CircuitCurrent");
    model.physics("mf").feature("mtcd1").set("N", "N");
    model.physics("cir").feature("V1").set("Connections", new String[][]{{"1"}, {"0"}});
    model.physics("cir").feature("V1").set("sourceType", "SineSource");
    model.physics("cir").feature("V1").set("value", "Vac");
    model.physics("cir").feature("V1").set("offset", "0.0");
    model.physics("cir").feature("V1").set("freq", "nu");
    model.physics("cir").feature("V1").label("Voltage Source V1");
    model.physics("cir").feature("IvsU1").set("V_src", "root.comp1.mf.VCoil_1");
    model.physics("cir").feature("IvsU1").set("Connections", new String[][]{{"2"}, {"1"}});
    model.physics("cir").feature("R1").set("R", "R");
    model.physics("cir").feature("R1").set("Connections", new String[][]{{"3"}, {"2"}});
    model.physics("cir").feature("R1").label("Resistor R1");
    model.physics("cir").feature("L1").set("L", "L");
    model.physics("cir").feature("L1").set("Connections", new String[][]{{"0"}, {"3"}});

    model.mesh("mesh1").feature("ftet1").feature("size1").set("custom", "on");
    model.mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "20");
    model.mesh("mesh1").run();

    model.frame("material1").sorder(1);

    model.study().create("std1");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").create("time", "Transient");

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").attach("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").create("su1", "StoreSolution");
    model.sol("sol1").create("st2", "StudyStep");
    model.sol("sol1").create("v2", "Variables");
    model.sol("sol1").create("t1", "Time");
    model.sol("sol1").feature("s1").create("se1", "Segregated");
    model.sol("sol1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("s1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").feature("t1").create("se1", "Segregated");
    model.sol("sol1").feature("t1").create("i1", "Iterative");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("t1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").create("ss2", "SegregatedStep");
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ssDef");
    model.sol("sol1").feature("t1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").create("va1", "Vanka");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").create("va1", "Vanka");
    model.sol("sol1").feature("t1").feature().remove("fcDef");

    model.result().dataset().create("dset3", "Solution");
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset().create("dset5", "Solution");
    model.result().dataset().create("dset6", "Solution");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set(new int[]{2, 3, 5, 6});
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().set(new int[]{4, 7});
    model.result().dataset("dset5").selection().geom("geom1", 3);
    model.result().dataset("dset5").selection().set(new int[]{4});
    model.result().dataset("dset6").selection().named("sel2");
    model.result().create("pg1", "PlotGroup3D");
    model.result().create("pg2", "PlotGroup3D");
    model.result().create("pg3", "PlotGroup3D");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("data", "dset3");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg3").create("slc1", "Slice");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("slc1", "Slice");
    model.result("pg4").create("slc2", "Slice");
    model.result("pg4").create("slc3", "Slice");
    model.result("pg4").create("slc4", "Slice");
    model.result("pg4").create("arwv1", "ArrowVolume");
    model.result().export().create("img1", "Image3D");
    model.result().export().create("plot1", "Plot");
    model.result().export().create("plot2", "Plot");
    model.result().export().create("plot3", "Plot");
    model.result().export().create("plot4", "Plot");
    model.result().export().create("img2", "Image3D");
    model.result().export().create("img3", "Image3D");

    model.study("std1").feature("time").set("rtol", "0.001");
    model.study("std1").feature("time").set("tlist", "range(0,5e-5,0.05)");
    model.study("std1").feature("time").set("rtolactive", true);

    model.sol("sol1").attach("std1");
    model.sol("sol1").feature("s1").set("probesel", "none");
    model.sol("sol1").feature("s1").feature("se1").set("segterm", "itertol");
    model.sol("sol1").feature("s1").feature("se1").set("segiter", "6");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_mf_mtcd1_ccc1_s"});
    model.sol("sol1").feature("s1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_mf_mtcd1_ccc1_p", "comp1_mf_mtcd1_ccc1_lm"});
    model.sol("sol1").feature("st2").set("studystep", "time");
    model.sol("sol1").feature("v2").set("initsol", "sol1");
    model.sol("sol1").feature("v2").set("solnum", "auto");
    model.sol("sol1").feature("v2").set("notsoluse", "sol2");
    model.sol("sol1").feature("v2").set("initmethod", "sol");
    model.sol("sol1").feature("v2").set("notsolmethod", "sol");
    model.sol("sol1").feature("v2").set("notsol", "sol1");
    model.sol("sol1").feature("v2").set("notsolnum", "auto");
    model.sol("sol1").feature("v2").set("notsoluse", "sol2");
    model.sol("sol1").feature("t1").set("control", "time");
    model.sol("sol1").feature("t1").set("tlist", "range(0,5e-5,0.05)");
    model.sol("sol1").feature("t1").set("rtol", "0.001");
    model.sol("sol1").feature("t1").feature("se1").active(false);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_currents", "comp1_current_time"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2")
         .set("segvar", new String[]{"comp1_A", "comp1_mf_psi"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("linsolver", "i1");
    model.sol("sol1").feature("t1").feature("i1").set("prefuntype", "right");
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("pr").feature("va1")
         .set("vankavars", new String[]{"comp1_mf_psi"});
    model.sol("sol1").feature("t1").feature("i1").feature("mg1").feature("po").feature("va1")
         .set("vankavars", new String[]{"comp1_mf_psi"});
    model.sol("sol1").feature("t1").feature("fc1").active(true);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", "25");
    model.sol("sol1").runAll();

    model.result().dataset("dset6").selection().label("Removable");
    model.result("pg1").feature("arwv1").set("descr", "Current density");
    model.result("pg1").feature("arwv1").set("scale", "3.687916611792018E-5");
    model.result("pg1").feature("arwv1").set("xnumber", "10");
    model.result("pg1").feature("arwv1").set("ynumber", "10");
    model.result("pg1").feature("arwv1").set("znumber", "5");
    model.result("pg1").feature("arwv1").set("color", "blue");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"mf.Jx", "mf.Jy", "mf.Jz"});
    model.result("pg1").feature("arwv1").set("scaleactive", false);
    model.result("pg2").set("looplevel", new String[]{"595"});
    model.result("pg2").feature("slc1").set("quickynumber", "1");
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("arwv1").set("arrowtype", "cone");
    model.result("pg2").feature("arwv1").set("scale", "30");
    model.result("pg2").feature("arwv1").set("xnumber", "10");
    model.result("pg2").feature("arwv1").set("ynumber", "1");
    model.result("pg2").feature("arwv1").set("znumber", "10");
    model.result("pg2").feature("arwv1").set("color", "black");
    model.result("pg2").feature("arwv1").set("scaleactive", true);
    model.result("pg3").feature("slc1").set("quickynumber", "1");
    model.result("pg3").feature("slc1").set("quickplane", "zx");
    model.result("pg4").label("Coil");
    model.result("pg4").set("looplevel", new String[]{"595"});
    model.result("pg4").set("showlegends", "off");
    model.result("pg4").feature("slc1").label("Slice 1_3");
    model.result("pg4").feature("slc1").set("quickplane", "xy");
    model.result("pg4").feature("slc1").set("quickzmethod", "coord");
    model.result("pg4").feature("slc1").set("quickz", "150");
    model.result("pg4").feature("slc2").label("Slice 4.1");
    model.result("pg4").feature("slc2").set("quickplane", "xy");
    model.result("pg4").feature("slc2").set("quickzmethod", "coord");
    model.result("pg4").feature("slc2").set("quickz", "105");
    model.result("pg4").feature("slc3").label("Slice 5");
    model.result("pg4").feature("slc3").set("quickplane", "xy");
    model.result("pg4").feature("slc3").set("quickzmethod", "coord");
    model.result("pg4").feature("slc3").set("quickz", "20");
    model.result("pg4").feature("slc4").label("Slice 2");
    model.result("pg4").feature("slc4").set("quickxmethod", "coord");
    model.result("pg4").feature("arwv1").active(false);
    model.result("pg4").feature("arwv1").set("xnumber", "14");
    model.result("pg4").feature("arwv1").set("scale", "22.805154361053216");
    model.result("pg4").feature("arwv1").set("scaleactive", false);
    model.result("pg4").feature("slc2").label("Slice 4");
    model.result().export("img1").label("Whole_Slice");
    model.result().export("img1").set("pngfilename", "Whole_Slice");
    model.result().export("img1").set("legend", true);
    model.result().export("img1").set("plotgroup", "pg2");
    model.result().export("img1").set("printunit", "mm");
    model.result().export("img1").set("webunit", "px");
    model.result().export("img1").set("printheight", "90");
    model.result().export("img1").set("webheight", "600");
    model.result().export("img1").set("printwidth", "120");
    model.result().export("img1").set("webwidth", "800");
    model.result().export("img1").set("printlockratio", "off");
    model.result().export("img1").set("weblockratio", "off");
    model.result().export("img1").set("printresolution", "300");
    model.result().export("img1").set("webresolution", "96");
    model.result().export("img1").set("size", "current");
    model.result().export("img1").set("antialias", "on");
    model.result().export("img1").set("zoomextents", "off");
    model.result().export("img1").set("title", "off");
    model.result().export("img1").set("legend", "on");
    model.result().export("img1").set("logo", "on");
    model.result().export("img1").set("options", "on");
    model.result().export("img1").set("fontsize", "9");
    model.result().export("img1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("img1").set("background", "transparent");
    model.result().export("img1").set("axisorientation", "on");
    model.result().export("img1").set("grid", "on");
    model.result().export("img1").set("qualitylevel", "92");
    model.result().export("img1").set("qualityactive", "off");
    model.result().export("img1").set("imagetype", "png");
    model.result().export("plot1").set("plotgroup", "pg4");
    model.result().export("plot1").set("filename", "Slice_1_3_40_20");
    model.result().export("plot2").set("plotgroup", "pg4");
    model.result().export("plot2").set("filename", "Slice_4_40_20");
    model.result().export("plot2").set("plot", "slc2");
    model.result().export("plot3").set("plotgroup", "pg4");
    model.result().export("plot3").set("filename", "Slice_5_40_20");
    model.result().export("plot3").set("plot", "slc3");
    model.result().export("plot4").set("plotgroup", "pg4");
    model.result().export("plot4").set("filename", "Slice_2_40_20");
    model.result().export("plot4").set("plot", "slc4");
    model.result().export("img2").set("plotgroup", "pg4");
    model.result().export("img2").set("pngfilename", "magnetic_arrow");
    model.result().export("img2").set("printunit", "mm");
    model.result().export("img2").set("webunit", "px");
    model.result().export("img2").set("printheight", "90");
    model.result().export("img2").set("webheight", "600");
    model.result().export("img2").set("printwidth", "120");
    model.result().export("img2").set("webwidth", "800");
    model.result().export("img2").set("printlockratio", "off");
    model.result().export("img2").set("weblockratio", "off");
    model.result().export("img2").set("printresolution", "300");
    model.result().export("img2").set("webresolution", "96");
    model.result().export("img2").set("size", "current");
    model.result().export("img2").set("antialias", "on");
    model.result().export("img2").set("zoomextents", "off");
    model.result().export("img2").set("title", "off");
    model.result().export("img2").set("legend", "off");
    model.result().export("img2").set("logo", "on");
    model.result().export("img2").set("options", "on");
    model.result().export("img2").set("fontsize", "9");
    model.result().export("img2").set("customcolor", new double[]{1, 1, 1});
    model.result().export("img2").set("background", "transparent");
    model.result().export("img2").set("axisorientation", "on");
    model.result().export("img2").set("grid", "on");
    model.result().export("img2").set("qualitylevel", "92");
    model.result().export("img2").set("qualityactive", "off");
    model.result().export("img2").set("imagetype", "png");
    model.result().export("img3").set("plotgroup", "pg4");
    model.result().export("img3").set("pngfilename", "horizontal");
    model.result().export("img3").set("printunit", "mm");
    model.result().export("img3").set("webunit", "px");
    model.result().export("img3").set("printheight", "90");
    model.result().export("img3").set("webheight", "600");
    model.result().export("img3").set("printwidth", "120");
    model.result().export("img3").set("webwidth", "800");
    model.result().export("img3").set("printlockratio", "off");
    model.result().export("img3").set("weblockratio", "off");
    model.result().export("img3").set("printresolution", "300");
    model.result().export("img3").set("webresolution", "96");
    model.result().export("img3").set("size", "current");
    model.result().export("img3").set("antialias", "on");
    model.result().export("img3").set("zoomextents", "off");
    model.result().export("img3").set("title", "off");
    model.result().export("img3").set("legend", "off");
    model.result().export("img3").set("logo", "on");
    model.result().export("img3").set("options", "on");
    model.result().export("img3").set("fontsize", "9");
    model.result().export("img3").set("customcolor", new double[]{1, 1, 1});
    model.result().export("img3").set("background", "transparent");
    model.result().export("img3").set("axisorientation", "on");
    model.result().export("img3").set("grid", "on");

    return model;
  }

  public static Model run2(Model model, int voltage, int overlap) {
    model.result().export("img3").set("qualitylevel", "92");
    model.result().export("img3").set("qualityactive", "off");
    model.result().export("img3").set("imagetype", "png");
    String folder = "D:\\xuhao.du.UWA\\Peter Du\\transformer\\FEM\\Comsol\\";
    model.geom("geom1").feature("wp1").geom().run("co1");
    model.geom("geom1").run("fin");
	String stringO = String.format("%d", overlap);
    model.param().set("remove", stringO);
	String stringV = String.format("%d[V]", voltage);
    model.param().set("Vac", stringV);

    model.geom("geom1").run("fin");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg4").feature("slc2").active(false);
    model.result("pg4").feature("slc3").active(false);
    model.result("pg4").feature("slc4").active(false);
    model.result("pg4").feature("arwv1").active(false);
    model.result("pg4").set("window", "graphics");
    model.result("pg4").run();
    model.result("pg4").set("window", "graphics");
    model.result("pg4").set("windowtitle", "");
    model.result("pg4").feature("slc2").active(true);
    model.result("pg4").feature("slc3").active(true);
    model.result("pg4").feature("slc4").active(true);
    model.result("pg4").feature("arwv1").active(false);
	String stringFN = String.format("%sSlice_1_3_O%d_V%d", folder, overlap, voltage);
    model.result().export("plot1").set("filename", stringFN);
    model.result().export("plot1").run();
    model.result("pg4").feature("slc1").active(false);
    model.result("pg4").feature("slc3").active(false);
    model.result("pg4").feature("slc4").active(false);
    model.result("pg4").feature("arwv1").active(false);
    model.result("pg4").set("window", "graphics");
    model.result("pg4").run();
    model.result("pg4").set("window", "graphics");
    model.result("pg4").set("windowtitle", "");
    model.result("pg4").feature("slc1").active(true);
    model.result("pg4").feature("slc3").active(true);
    model.result("pg4").feature("slc4").active(true);
    model.result("pg4").feature("arwv1").active(false);
	stringFN = String.format("%sSlice_4_O%d_V%d", folder, overlap, voltage);
    model.result().export("plot2").set("filename", stringFN);
    model.result().export("plot2").run();
    model.result("pg4").feature("slc1").active(false);
    model.result("pg4").feature("slc2").active(false);
    model.result("pg4").feature("slc4").active(false);
    model.result("pg4").feature("arwv1").active(false);
    model.result("pg4").set("window", "graphics");
    model.result("pg4").run();
    model.result("pg4").set("window", "graphics");
    model.result("pg4").set("windowtitle", "");
    model.result("pg4").feature("slc1").active(true);
    model.result("pg4").feature("slc2").active(true);
    model.result("pg4").feature("slc4").active(true);
    model.result("pg4").feature("arwv1").active(false);
	stringFN = String.format("%sSlice_5_O%d_V%d", folder, overlap, voltage);
    model.result().export("plot3").set("filename", stringFN);
    model.result().export("plot3").run();
    model.result("pg4").feature("slc1").active(false);
    model.result("pg4").feature("slc2").active(false);
    model.result("pg4").feature("slc3").active(false);
    model.result("pg4").feature("arwv1").active(false);
    model.result("pg4").set("window", "graphics");
    model.result("pg4").run();
    model.result("pg4").set("window", "graphics");
    model.result("pg4").set("windowtitle", "");
    model.result("pg4").feature("slc1").active(true);
    model.result("pg4").feature("slc2").active(true);
    model.result("pg4").feature("slc3").active(true);
    model.result("pg4").feature("arwv1").active(false);
	stringFN = String.format("%sSlice_2_O%d_V%d", folder, overlap, voltage);
    model.result().export("plot4").set("filename", stringFN);
    model.result().export("plot4").run();
	stringFN = String.format("Finish processing condition with Overlap: %d and Voltage: %d", overlap, voltage);
    System.out.println(stringFN);
    return model;
  }

  public static void main(String[] args) {
	System.out.println("Starting building model!");
    Model model = run();
	System.out.println("Finish building model!");
	for (int ol=36; ol<=115; ol=ol+90){
		for (int V=41; V<=200; V=V+170) {
			run2(model, V, ol);
		}
	}
  }

}
