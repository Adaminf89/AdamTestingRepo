//
//  DetailViewController.swift
//  AdamInfiesto_Research
//
//  Created by Adam Infiesto on 9/28/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {

    @IBOutlet weak var daAuthor: UILabel!
    @IBOutlet weak var daTitle: UILabel!
    @IBOutlet weak var desc: UITextView!
    var story : News!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        daAuthor.text = story.info
        daTitle.text = story.title
        desc.text = story.desc
        
        //set the detail view to show small titles
        navigationItem.largeTitleDisplayMode = .never
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
