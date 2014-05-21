/**
 * @Copyright MNTSOFT
 */
package com.mnt.erp.service;

import java.util.List;

import com.mnt.erp.dao.GLJournalDao;

/**
 * @author Naresh
 * @version 1.0 04-01-2014
 */
public class GLJournalServiceImpl implements GLJournalService {

	List<Object[]> objects = null;
	List<Object> obj = null;
	boolean flag = true;

	GLJournalDao glJournalDao;

	public GLJournalDao getGlJournalDao() {
		return glJournalDao;
	}

	public void setGlJournalDao(GLJournalDao glJournalDao) {
		this.glJournalDao = glJournalDao;
	}

	@Override
	public boolean saveGLJournal(Object object) {
		try {

			flag = glJournalDao.saveGLJournal(object);

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object[]> searchGLJournal() {
		try {
			objects = glJournalDao.searchGLJournal();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public List<Object> searchGLJournalWithId(int glId) {
		try {
			obj = glJournalDao.searchGLJournalWithId(glId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean updateGLJournal(Object object) {
		try {
			flag = glJournalDao.updateGLJournal(object);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteGLJournal(int glId) {
		try {
			flag = glJournalDao.deleteGLJournal(glId);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean deleteGLJournalLine(int gljId) {
		try {
			flag = glJournalDao.deleteGLJournalLine(gljId);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Object[]> basicSearchGLJournal(String label, String operator,
			String searchName) {
		try {
			objects = glJournalDao.basicSearchGLJournal(label, operator,
					searchName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objects;
	}

}
