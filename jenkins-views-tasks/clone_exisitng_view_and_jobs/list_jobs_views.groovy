import jenkins.model.Jenkins

// Define the view name and the parent view if needed
def parentViewName = 'SPRO'  // Replace with the parent view name if nested, or use null for top-level view
def viewName = 'SPRO_11_1'   // Replace with the view name you want to list jobs from

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

// List all jobs in the view
println "Jobs in view '${viewName}':"
targetView.getItems().each { job ->
    println "- ${job.name}"
}
