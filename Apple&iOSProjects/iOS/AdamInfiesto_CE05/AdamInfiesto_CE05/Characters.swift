//
//  Characters.swift
//  AdamInfiesto_CE05
//
//  Created by Adam Infiesto on 10/17/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import Foundation
import UIKit

class Characterz
{
    var Name : String
    var image : UIImage
    var Descriptions : String
    
    
    init(Name: String, Descriptions: String = String(), image : UIImage = UIImage() )
    {
        self.Name = Name
        self.image = image
        self.Descriptions = Descriptions
    }
}
