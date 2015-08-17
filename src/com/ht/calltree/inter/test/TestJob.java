package com.ht.calltree.inter.test;

public class TestJob {
	public void execute() {
		try {
			System.out.println("!!!task run !!!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
