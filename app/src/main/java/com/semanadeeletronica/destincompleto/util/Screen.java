package com.semanadeeletronica.destincompleto.util;//package com.miligrama.alineborges.miligrama.util;

import com.semanadeeletronica.destincompleto.DetalhesActivity;
import com.semanadeeletronica.destincompleto.GitHubActivity;
import com.semanadeeletronica.destincompleto.MainActivity;

/**
 * Created by Aline Borges on 8/26/2014.
 */
public enum Screen {
	Main(MainActivity.class),
    Detalhes(DetalhesActivity.class),
    Github(GitHubActivity.class);
	
	private Class activityClass;
	
	Screen(Class activityClass) {
		this.activityClass = activityClass;
	}
	
	public Class getActivityClass() {
		return this.activityClass;
	}
	
}
