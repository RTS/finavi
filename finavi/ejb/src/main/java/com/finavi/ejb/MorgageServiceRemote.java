package com.finavi.ejb;

import java.util.List;

import com.finavi.model.Scoring;
import com.finavi.model.ScoringRequest;

public interface MorgageServiceRemote {
	
	public List<Scoring> calculateScorings(ScoringRequest request);
	
}
