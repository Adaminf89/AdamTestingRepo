//
//  DetailViewController.swift
//  InfiestoAdam_CE5
//
//  Created by Adam Infiesto on 9/19/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

class DetailViewController: UIViewController {
    
    @IBOutlet weak var topBar: UINavigationItem!
    
    @IBOutlet weak var DaImage: UIImageView!
    
    @IBOutlet weak var datitle: UILabel!
    
    @IBOutlet weak var name: UILabel!
    
    @IBOutlet weak var party: UILabel!
    
    @IBOutlet weak var state: UILabel!
    
    
    var member : Congress!
    
    override func viewDidLoad()
    {
        super.viewDidLoad()
        
        topBar.title = member.name
        DaImage.image = member.pic
        datitle.text = member.title
        name.text = member.name
        party.text = member.party
        state.text = member.State
        
        
        // datitle.text = post.title
        //date.text = post.date.description
        //textinfo.text = post.postText
        
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

