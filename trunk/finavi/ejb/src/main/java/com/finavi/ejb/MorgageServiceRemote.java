package com.finavi.ejb;

import java.util.List;

import com.finavi.model.Scoring;
import com.finavi.model.ScoringRequest;
import com.finavi.model.User;

public interface MorgageServiceRemote {
	/**
	 * Vypocita scoring podla requestu a vrati ich zoznam.
	 * @param request request s udajmi z formulara.
	 * @return list vypocitanych scoringov.
	 */
	public List<Scoring> calculateScorings(ScoringRequest request);
	/**
	 * Vrati aktualne scoringy pouzivatela. Ak Ziadne nema, vrati prazdny list.
	 * @param u User pre ktoreho treba vrat scoringy.
	 * @return list scoringov.
	 */
	public List<Scoring> getActualScoringsOfUser(User u);
}
