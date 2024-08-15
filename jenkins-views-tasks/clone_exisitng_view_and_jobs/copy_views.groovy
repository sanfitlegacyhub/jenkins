import hudson.model.ListView
import jenkins.model.Jenkins

// Define the parent view, original view, and new view names
def parentViewName = 'SPRO'
def originalViewName = 'SPRO_1015'
def newViewName = 'SPRO_11_1'

// Get Jenkins instance
def jenkins = Jenkins.instance

// Get the parent view
def parentView = jenkins.getView(parentViewName)
if (parentView == null) {
    println "Parent view '${parentViewName}' does not exist."
    return
}

// Check if the original view exists within the parent view
def originalView = parentView.getView(originalViewName)
if (originalView == null) {
    println "Original view '${originalViewName}' does not exist within '${parentViewName}'."
    return
}

// Create the new view inside the parent view
def newView = new ListView(newViewName, parentView)
parentView.addView(newView)

// Copy all jobs from the original view to the new view
originalView.getItems().each { job ->
    try {
        newView.add(job)
        println "Added job '${job.name}' to new view '${newViewName}'."
    } catch (Exception e) {
        println "Failed to add job '${job.name}' to new view: ${e.message}"
    }
}

// Save the changes
jenkins.save()

println "New view '${newViewName}' created inside '${parentViewName}' with jobs copied from '${originalViewName}'."
