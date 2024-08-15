import jenkins.model.Jenkins

// Define the parent view name if needed and the target view name
def parentViewName = 'SPRO' // Replace with the parent view name if nested, or use null for top-level view
def viewName = 'SPRO_11_1'  // The view name containing the jobs you want to rename
def oldSuffix = '1015'       // The old suffix to replace
def newSuffix = '11_1'       // The new suffix to use in the job names

// Get Jenkins instance
def jenkins = Jenkins.instance

// Get the parent view
def parentView = parentViewName ? jenkins.getView(parentViewName) : jenkins

if (parentView == null) {
    println "Parent view '${parentViewName}' does not exist."
    return
}

// Get the target view
def targetView = parentView.getView(viewName)

if (targetView == null) {
    println "View '${viewName}' does not exist."
    return
}

// Loop through all jobs in the view and rename them by replacing the suffix
targetView.getItems().each { job ->
    def oldJobName = job.name
    if (oldJobName.contains(oldSuffix)) {
        def newJobName = oldJobName.replace(oldSuffix, newSuffix)
        
        try {
            println "Renaming job '${oldJobName}' to '${newJobName}'..."
            job.renameTo(newJobName)
        } catch (Exception e) {
            println "Failed to rename job '${oldJobName}': ${e.message}"
        }
    } else {
        println "Job '${oldJobName}' does not contain the suffix '${oldSuffix}', skipping..."
    }
}

// Save the changes
jenkins.save()

println "All applicable jobs in view '${viewName}' have been renamed by replacing '${oldSuffix}' with '${newSuffix}'."
