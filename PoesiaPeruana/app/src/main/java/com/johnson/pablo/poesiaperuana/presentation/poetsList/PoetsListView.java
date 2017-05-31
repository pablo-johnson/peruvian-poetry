package com.johnson.pablo.poesiaperuana.presentation.poetsList;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.presentation.common.PoetsView;

import java.util.List;

/**
 * Created by pjohnson on 9/04/17.
 */
public interface PoetsListView extends PoetsView {
    void populatePoetsList(List<Poet> poets);
}
